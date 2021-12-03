package util;

import javax.swing.text.*;

public class Limitador extends PlainDocument {
    private int limite; /* Máximo de caracteres permitidos */
    
    public Limitador(int limite) {
		this.limite=limite;
		
	}
/* Método chamado quando se tenta inserir um texto */
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
/* Calcula quantos caracteres podem ser inseridos antes de atingir o limite */
        int sobra = limite - getLength();
/* Calcula o comprimento máximo da string que pode ser inserida, sem que o limite seja quebrado. Se ainda puderem ser inseridos dois caracteres, mas alguém tentar inserir a string "abcd", isto vai fazer com que somente a string "ab" seja inserida */
        int comprimento = (sobra > str.length())?str.length():sobra;
/* Isso faz com que a string resultante seja realmente inserida */
        super.insertString(offs, str.substring(0, comprimento), a);
    }
}