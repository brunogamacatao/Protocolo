import protocolo.*

class BootStrap {

    def init = { servletContext ->
    	//Cria os tipos de permissões
    	def adminRole = Grupo.findByAuthority('ROLE_ADMIN') ?: new Grupo(authority: 'ROLE_ADMIN').save(failOnError: true)
    	def alunoRole = Grupo.findByAuthority('ROLE_ALUNO') ?: new Grupo(authority: 'ROLE_ALUNO').save(failOnError: true)
    	
    	def adminUser = Usuario.findByUsername('admin') ?: new Usuario(
    		username: 'admin',
    		password: 'admin',
    		enabled: true).save(failOnError:true)

    	//Define admin para o grupo admin
    	if(!adminUser.authorities.contains(adminRole)){
    		UsuarioGrupo.create adminUser, adminRole
    	}
    }
    def destroy = {
    }
}
