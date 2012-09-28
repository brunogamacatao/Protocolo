package protocolo

import org.springframework.dao.DataIntegrityViolationException

class SolicitacaoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def mailService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [solicitacaoInstanceList: Solicitacao.list(params), solicitacaoInstanceTotal: Solicitacao.count()]
    }

    def create() {
        [solicitacaoInstance: new Solicitacao(params)]
    }

    def save() {
        def solicitacaoInstance = new Solicitacao(params)
        if (!solicitacaoInstance.save(flush: true)) {
            render(view: "create", model: [solicitacaoInstance: solicitacaoInstance])
            return
        }

        def para = solicitacaoInstance.setor.responsavel

        mailService.sendMail {
           to solicitacaoInstance.solicitante.email
           from "facisaprotocolo@gmail.com"
           subject "Solicitação Criada"
           body (view: '/emails/solicitacaoCriada',
                 model: [solicitacao: solicitacaoInstance])
        }

        mailService.sendMail {
           to solicitacaoInstance.setor.responsavel.email
           from "facisaprotocolo@gmail.com"
           subject "Nova Solicitação"
           body (view: '/emails/novaSolicitacao',
                 model: [solicitacao: solicitacaoInstance])
        }
                
        flash.message = message(code: 'default.created.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), solicitacaoInstance.id])
        redirect(action: "show", id: solicitacaoInstance.id)
    }

    def show(Long id) {
        def solicitacaoInstance = Solicitacao.get(id)
        if (!solicitacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "list")
            return
        }

        [solicitacaoInstance: solicitacaoInstance]
    }
    
    def aceitar(Long id) {
        def solicitacaoInstance = Solicitacao.get(id)
        solicitacaoInstance.estado = 'ATENDIMENTO'
        solicitacaoInstance.save()
        
        mailService.sendMail {
           to solicitacaoInstance.solicitante.email
           from "facisaprotocolo@gmail.com"
           subject "Solicitação em Atendimento"
           body (view: '/emails/solicitacaoEmAtendimento',
                 model: [solicitacao: solicitacaoInstance])
        }

        redirect(action: "edit", id: solicitacaoInstance.id)
    }

    def edit(Long id) {
        def solicitacaoInstance = Solicitacao.get(id)
        if (!solicitacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "list")
            return
        }

        [solicitacaoInstance: solicitacaoInstance]
    }

    def update(Long id, Long version) {
        def solicitacaoInstance = Solicitacao.get(id)
        if (!solicitacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (solicitacaoInstance.version > version) {
                solicitacaoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'solicitacao.label', default: 'Solicitacao')] as Object[],
                          "Another user has updated this Solicitacao while you were editing")
                render(view: "edit", model: [solicitacaoInstance: solicitacaoInstance])
                return
            }
        }

        solicitacaoInstance.properties = params

        if (!solicitacaoInstance.save(flush: true)) {
            render(view: "edit", model: [solicitacaoInstance: solicitacaoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), solicitacaoInstance.id])
        redirect(action: "show", id: solicitacaoInstance.id)
    }

    def delete(Long id) {
        def solicitacaoInstance = Solicitacao.get(id)
        if (!solicitacaoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "list")
            return
        }

        try {
            solicitacaoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'solicitacao.label', default: 'Solicitacao'), id])
            redirect(action: "show", id: id)
        }
    }
}
