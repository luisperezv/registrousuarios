package cl.bci.registrousuarios.exception;

/**
 * Excepcion lanzada cuando sucede un error de negocio
 *
 * @author everis
 * <p>
 * <B> Todos los derechos reservados por Banco de Crédito e Inversiones </B>
 * <p>
 */
public class ErrorNegocioException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String codigo;

    public ErrorNegocioException(String codigo, String mensaje){
        super(mensaje);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return getMessage();
    }
}
