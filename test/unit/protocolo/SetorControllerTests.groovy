package protocolo



import org.junit.*
import grails.test.mixin.*

@TestFor(SetorController)
@Mock(Setor)
class SetorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/setor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.setorInstanceList.size() == 0
        assert model.setorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.setorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.setorInstance != null
        assert view == '/setor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/setor/show/1'
        assert controller.flash.message != null
        assert Setor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/setor/list'

        populateValidParams(params)
        def setor = new Setor(params)

        assert setor.save() != null

        params.id = setor.id

        def model = controller.show()

        assert model.setorInstance == setor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/setor/list'

        populateValidParams(params)
        def setor = new Setor(params)

        assert setor.save() != null

        params.id = setor.id

        def model = controller.edit()

        assert model.setorInstance == setor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/setor/list'

        response.reset()

        populateValidParams(params)
        def setor = new Setor(params)

        assert setor.save() != null

        // test invalid parameters in update
        params.id = setor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/setor/edit"
        assert model.setorInstance != null

        setor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/setor/show/$setor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        setor.clearErrors()

        populateValidParams(params)
        params.id = setor.id
        params.version = -1
        controller.update()

        assert view == "/setor/edit"
        assert model.setorInstance != null
        assert model.setorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/setor/list'

        response.reset()

        populateValidParams(params)
        def setor = new Setor(params)

        assert setor.save() != null
        assert Setor.count() == 1

        params.id = setor.id

        controller.delete()

        assert Setor.count() == 0
        assert Setor.get(setor.id) == null
        assert response.redirectedUrl == '/setor/list'
    }
}
