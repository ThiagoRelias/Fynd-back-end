package fynd_api.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(Long id) {
        super("Item não encontrado com id " + id);
    }
}