package protocolo

class Pessoa {
  String nome
  String email

  static constraints = {
    nome(blank: false)
    email(blank: false, email: true)
  }
  
  String toString() {
    nome
  }
}
