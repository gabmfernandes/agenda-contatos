/**
 * Confirmacao de exclusao
 *	  @author Gabriel
 *	  @param id
 */

function confirmar(id) {
	let resposta = confirm("Confirma exclusão deste contato?");

	if (resposta) {
		window.location.href = "delete?id=" + id
	}
}