package protocolo

import org.springframework.dao.DataIntegrityViolationException

class SetorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [setorInstanceList: Setor.list(params), setorInstanceTotal: Setor.count()]
    }

    def create() {
        [setorInstance: new Setor(params)]
    }

    def save() {
        def setorInstance = new Setor(params)
        if (!setorInstance.save(flush: true)) {
            render(view: "create", model: [setorInstance: setorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'setor.label', default: 'Setor'), setorInstance.id])
        redirect(action: "show", id: setorInstance.id)
    }

    def show(Long id) {
        def setorInstance = Setor.get(id)
        if (!setorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "list")
            return
        }

        [setorInstance: setorInstance]
    }

    def edit(Long id) {
        def setorInstance = Setor.get(id)
        if (!setorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "list")
            return
        }

        [setorInstance: setorInstance]
    }

    def update(Long id, Long version) {
        def setorInstance = Setor.get(id)
        if (!setorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (setorInstance.version > version) {
                setorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'setor.label', default: 'Setor')] as Object[],
                          "Another user has updated this Setor while you were editing")
                render(view: "edit", model: [setorInstance: setorInstance])
                return
            }
        }

        setorInstance.properties = params

        if (!setorInstance.save(flush: true)) {
            render(view: "edit", model: [setorInstance: setorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'setor.label', default: 'Setor'), setorInstance.id])
        redirect(action: "show", id: setorInstance.id)
    }

    def delete(Long id) {
        def setorInstance = Setor.get(id)
        if (!setorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "list")
            return
        }

        try {
            setorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'setor.label', default: 'Setor'), id])
            redirect(action: "show", id: id)
        }
    }
}
