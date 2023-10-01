/**
 * 
 */
package util.adts;

/**
 * @author fc58199 fc58193
 * @param <E>
 *
 */
public interface QListWithSelection<E> extends QList<E> {
	
	/**
	 * Seleciona o elemento na posicao i da lista
	 * @param i = indice da posicao a selecionar
	 * @requires 0 <= i < size()
	 */
	void select(int i);
	
	
	/**
	 * adiciona o elemento que passa a ser o elemento selecionado
	 * @param e = objeto do tipo abstrato E a ser adicionado
	 *
	 */
	@Override
	void add(E e);
	
	
	/**
	 * Indica se há um elemento selecionado
	 * @return true = se há um elemento selecionado/ false = caso contrario
	 *
	 */
	boolean someSelected();
	
	
	/**
	 * assumindo someSelected(), devolve a posicao do elemento selecionado
	 * @return i = indíce da posicão selecionada
	 *
	 */
	int getIndexSelected();
	
	
	/**
	 * apaga o elemento selecionado, se someSelected() e não faz  nada no caso contrário
	 * 
	 *
	 */
	void remove();
	
	
	/**
	 * Assumindo que há um elemento selecionado (someSelected()), devolve-o
	 * @return E e = objeto selecionado
	 *
	 */
	E getSelected();
	
	
	
	
	/**
	 * Seleciona o elemento na posição aseguir à selecionada
	 * 
	 *
	 */
	void next();
	
	
	/**
	 * Seleciona o elemento na posição anterior à selecionada
	 * 
	 *
	 */
	void previous();
	
	

}

