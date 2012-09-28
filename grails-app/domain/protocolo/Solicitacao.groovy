package protocolo

class Solicitacao {
  Pessoa solicitante
  Setor setor
  String descricao
  Date dateCreated
  Date lastUpdated
  String estado = 'CRIADO'
  
  static constraints = {
    solicitante(blank: false)
    setor(blank: false)
    descricao(blank: false, widget: 'textarea')
  }
}
