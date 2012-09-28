package protocolo

class Setor {
  String nome
  Pessoa responsavel
  
  static constraints = {
    nome(blank: false)
    responsavel(blank: false)
  }
  
  String toString() {
    nome
  }
}
