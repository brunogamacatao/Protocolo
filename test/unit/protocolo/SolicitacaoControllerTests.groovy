package protocolo



import org.junit.*
import grails.test.mixin.*

@TestFor(SolicitacaoController)
@Mock(Solicitacao)
class SolicitacaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/solicitacao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.solicitacaoInstanceList.size() == 0
        assert model.solicitacaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.solicitacaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.solicitacaoInstance != null
        assert view == '/solicitacao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/solicitacao/show/1'
        assert controller.flash.message != null
        assert Solicitacao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitacao/list'

        populateValidParams(params)
        def solicitacao = new Solicitacao(params)

        assert solicitacao.save() != null

        params.id = solicitacao.id

        def model = controller.show()

        assert model.solicitacaoInstance == solicitacao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitacao/list'

        populateValidParams(params)
        def solicitacao = new Solicitacao(params)

        assert solicitacao.save() != null

        params.id = solicitacao.id

        def model = controller.edit()

        assert model.solicitacaoInstance == solicitacao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/solicitacao/list'

        response.reset()

        populateValidParams(params)
        def solicitacao = new Solicitacao(params)

        assert solicitacao.save() != null

        // test invalid parameters in update
        params.id = solicitacao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/solicitacao/edit"
        assert model.solicitacaoInstance != null

        solicitacao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/solicitacao/show/$solicitacao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        solicitacao.clearErrors()

        populateValidParams(params)
        params.id = solicitacao.id
        params.version = -1
        controller.update()

        assert view == "/solicitacao/edit"
        assert model.solicitacaoInstance != null
        assert model.solicitacaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/solicitacao/list'

        response.reset()

        populateValidParams(params)
        def solicitacao = new Solicitacao(params)

        assert solicitacao.save() != null
        assert Solicitacao.count() == 1

        params.id = solicitacao.id

        controller.delete()

        assert Solicitacao.count() == 0
        assert Solicitacao.get(solicitacao.id) == null
        assert response.redirectedUrl == '/solicitacao/list'
    }
}
