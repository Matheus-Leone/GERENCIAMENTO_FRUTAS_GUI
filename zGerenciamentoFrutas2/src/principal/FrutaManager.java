package principal;
	//Imports da biblioteca java para serem utilizados no programa
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

	//Classe principal, onde está criando o Array e a lista para a inserção de frutas(Strings)
public class FrutaManager {
	private ArrayList<String> frutas;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	//Settando um nome para o Array e para a Lista
	public FrutaManager() {
		frutas = new ArrayList<>();
		listModel = new DefaultListModel<>();

	//Criação da janela do programa, tendo o tamanho e a função fechar para sair da janela 
		JFrame frame = new JFrame("Gerenciador de Frutas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,300);
		frame.setLayout(new BorderLayout());
		
	//Criação de painel para organização e layout
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
	//Criação de um campo de Texto no painel
		JTextField frutaField = new JTextField(15);
		panel.add(frutaField);
	//Criação de um botão de adicionar no painel
		JButton addButton = new JButton("Adicionar");
		panel.add(addButton);
	//Criação de um botão de modificar no painel
		JButton modifyButton = new JButton ("Modificar");
		modifyButton.setEnabled(false); //Inicialmente desabilita o botão pois não tem utilidade (nenhuma fruta para modificar)
		panel.add(modifyButton);
	//Criação de um botão de remover no painel
		JButton removeButton = new JButton ("Remover");
		removeButton.setEnabled(false); //Inicialmente desabilita o botão pois não tem utilidade (nenhuma fruta para remover)
		panel.add(removeButton);
	//Personalização da posição do painel
		frame.add(panel, BorderLayout.NORTH);
	//Criação de um modelo de lista (irá colocar todas as informações em lista) no centro do painel
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		frame.add(scrollPane, BorderLayout.CENTER);
		
	//Cria o Botão de Listar todas as frutas inseridas no parte inferior do painel 
		JButton listButton = new JButton("Listar Frutas");
		frame.add(listButton,BorderLayout.SOUTH);
		
	//Funciona somente depois do clique no botão		
	//Método para inserção de frutas no painel com algumas exigências
	//A partir do clique do botão, ele terá que preencher um espaço vazio, somente adicionando o nome
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
			String novaFruta = frutaField.getText();
			if(!novaFruta.isEmpty()) {
				frutas.add(novaFruta);
				listModel.addElement(novaFruta);
				frutaField.setText("");
				JOptionPane.showMessageDialog(frame, novaFruta + " foi adicionada.");
					}
			}
		});
	
	//Funciona somente depois do clique no botão
	//Método para modificação de frutas no painel com algumas exigências
	//Somente poderá ser modificado se o item foi selecionado pelo clique
		modifyButton.addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaSelecionada = listModel.getElementAt (selectedIndex);
					String novaFruta = JOptionPane.showInputDialog(frame, "Modificar: " + frutaSelecionada + " para: ", frutaSelecionada);
				if(novaFruta != null && !novaFruta.isEmpty()) {
					frutas.set(selectedIndex, novaFruta);
					listModel.set(selectedIndex, novaFruta);
					JOptionPane.showMessageDialog(frame, "Fruta" + frutaSelecionada + " foi modificada para: " + novaFruta);
				}
				
			}else {
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar");
				}	
			}
		});				
	
	//Funciona somente depois do clique no botão	
	//Método para remoção de frutas no painel com algumas exigências
	//Somente poderá ser removido se o item foi selecionado pelo clique	
		removeButton.addActionListener(new ActionListener() 	{
			@Override	
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex != -1) {
					String frutaRemovida = frutas.remove(selectedIndex);
					listModel.remove(selectedIndex);
					JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida");
				
				}else{
					JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover");
				}
				
			}
		
		});
	
	//Funciona somente depois do clique no botão
	//Método para demonstrar os itens do painel em lista
	//Caso não tenha nenhum item, ele irá entregar uma mensagem falando sobre
		listButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frutas.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista");
				}else {
					JOptionPane.showMessageDialog(frame, "Frutas: " + frutas);
				}
			}
		});
		
	//Determina que a modificação e a remoção somente poderão ser utilizadas se o item foi selecionado pelo clique	
		list.addListSelectionListener(e -> {
			boolean selectionExists = !list.isSelectionEmpty();
			removeButton.setEnabled(selectionExists);
			modifyButton.setEnabled(selectionExists);
			
		});
		
	//Coloca a interface para ser visível 
		frame.setVisible(true);
		}
	
	//Método principal para puxar tudo que foi settado para rodar
	//Override para sobrescrever as informações já settadas
		public static void main (String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new FrutaManager();
			}
			
		});
		
		
		}
	}
		
		
		
		
		
		
		
		
		
		