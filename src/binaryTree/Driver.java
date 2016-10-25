package binaryTree;

import javax.swing.*;

import java.awt.Component;
import java.awt.Container;
import java.awt.Panel;
import java.io.*;
	
	
	
	@SuppressWarnings("serial")
	public class Driver extends JFrame{
		
	
		final static JFileChooser chooser = new JFileChooser();
		static Component parent = null;
		
		
		JLabel enesimo = new JLabel("Enésimo: ");
		JLabel posicao = new JLabel("Posição: ");
		JLabel mediana = new JLabel("Mediana: ");
		JLabel cheia = new JLabel("Cheia: ");
		JLabel completa = new JLabel("Completa: ");
		JLabel imprima = new JLabel("Imprima: ");
		JLabel remova = new JLabel("Remova: ");
		
		final static JTextField t_enesimo = new JTextField();
		final static JTextField t_posicao = new JTextField();
		final static JTextField t_mediana = new JTextField();
		final static JTextField t_cheia = new JTextField();
		final static JTextField t_completa = new JTextField();
		final static JTextField t_imprima = new JTextField();
		final static JTextField t_remova = new JTextField();
		
		
		public Driver(){
			Container cont = this.getContentPane();
			setLayout(null);
			setSize(800,500);
			
			
			
			enesimo.setBounds(10,10,100,30);t_enesimo.setBounds(70,10,280,50);		 posicao.setBounds(375,10, 100, 30);t_posicao.setBounds(430,10,280,50);
			mediana.setBounds(10, 60, 100, 30);t_mediana.setBounds(70, 60, 280, 50); cheia.setBounds(375, 60, 100,30);  t_cheia.setBounds(430,60,280,50);
			completa.setBounds(10,110, 100,30);t_completa.setBounds(70, 110, 280,50);imprima.setBounds(375,110,100,30); t_imprima.setBounds(430,110,280,50);
			remova.setBounds(10,160,100,30);t_remova.setBounds(70,160,280,50);
			
			cont.add(enesimo);
			cont.add(t_enesimo);
			cont.add(posicao);
			cont.add(t_posicao);
			cont.add(mediana);
			cont.add(t_mediana);
			cont.add(cheia);
			cont.add(t_cheia);
			cont.add(completa);
			cont.add(t_completa);
			cont.add(imprima);
			cont.add(t_imprima);
			cont.add(remova);
			cont.add(t_remova);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		
		
		
		
		public static void main(String[] args) {
			No arvore = null;
			int numero = 0;
			String operacao = null;
			int parametro = 0;
			Driver d1 = new Driver();
			d1.setVisible(true);
			
			//ENTRADA

			try{
				chooser.setMultiSelectionEnabled(true);
				JOptionPane.showMessageDialog(null, "Adicione o arquivo com as entradas");
				
				
				try{
					chooser.showOpenDialog(parent);
				}catch (NullPointerException e){
					System.out.println(e.getStackTrace());
				}
				
				
				//Abrindo o arquivo passado via argumento
				FileReader fr = null;
				BufferedReader br = null;
				try{
					fr = new FileReader(chooser.getSelectedFile());
					br = new BufferedReader(fr);
				}catch (NullPointerException e){
					System.out.println(e.getStackTrace());
				}
				
				String linha = "";

				//Considerando que apenas uma linha do arquivo será levada em conta
				try{
					 linha = br.readLine();
					 System.out.println("Linha: " + linha);
				}catch (NullPointerException e){
					System.out.println(e.getMessage());
				}
				

				// while (linha != null){
					

					//Primeiro número da a raíz da Árvore
					try{
						numero = Integer.parseInt(linha.substring(0, linha.indexOf(" ")));
					}catch (StringIndexOutOfBoundsException e){
						System.out.println(e.getStackTrace());
					}
					
					arvore = new No(numero);

					linha = linha.substring(linha.indexOf(" ")+1, linha.length());

					//Adicionando números do arquivo restantes na árvore criada anteriormente
					while (linha.length() > 0){
						numero = Integer.parseInt(linha.substring(0, linha.indexOf(" ")));
						arvore.adicionar(numero);
						linha = linha.substring(linha.indexOf(" ")+1, linha.length());
					}

					// linha = br.readLine();
				// }
				try{
					fr.close();
				}catch (NullPointerException e){
					System.out.println(e.getStackTrace());
				}
			}catch (IOException ioe){
				System.out.println("Erro na abertura do arquivo: " + ioe.getMessage());
			}


			//INSTRUÃ‡Ã•ES
			
			JOptionPane.showMessageDialog(null, "Adicione o arquivo com as instruções");
			try{
				chooser.showOpenDialog(parent);
				FileReader fr;
				BufferedReader br = null;
				try{
					fr = new FileReader(chooser.getSelectedFile());
					br = new BufferedReader(fr);
				}catch (NullPointerException e){
					System.out.println("error");
				}
				
				String linha = "";
				try{
					linha  = br.readLine();
				}catch (NullPointerException e){
					System.out.println("error");
				}

				while (linha != null){
					System.out.println("Linha: " + linha);

					try{
						operacao = linha.substring(0, linha.indexOf(" "));
					}catch (StringIndexOutOfBoundsException e){
						System.out.println("erro1");
					}

					if (operacao == "ENESIMO" || operacao == "POSICAO" || operacao == "REMOVA"){
						linha = linha.substring(linha.indexOf(" ")+1, linha.length());
						parametro = Integer.parseInt(linha.substring(0, linha.length()));
					}
					String t_enesimo_text = "";
					String t_posicao_text = "";
					String t_mediana_text = "";  
					try{
						switch(operacao){
					
						case "IMPRIMA":
							t_imprima.setText(arvore.toString());
							break;
						case "ENESIMO":
							 t_enesimo_text = (Integer.toString((arvore.enesimoElemento(parametro))));
							 System.out.println("T_ENESINMO" +  t_enesimo_text);
							 t_enesimo.setText( t_enesimo_text);
							break;
						case "POSICAO":
							t_posicao.setText(Integer.toString(arvore.posicao(parametro)));
							break;
						case "REMOVA":
							t_remova.setText(arvore.remover(parametro) ? "Removido o valor " + parametro : "O valor não pode ser removido.");
							break;
						case "MEDIANA":
							t_mediana.setText(String.valueOf((arvore.mediana())));
							break;
						case "CHEIA":
							t_cheia.setText(arvore.ehCheia() ? "É Cheia" : "Não é cheia");
							break;
						case "COMPLETA":
							t_completa.setText(arvore.ehCompleta() ? "É Completa" : "Não é completa");
							break;	
					}
					}catch (NullPointerException e){
						System.out.println("erro");
					}
					try{
						linha = br.readLine();
					}catch(NullPointerException e){
						System.out.println("erro");
						break;
					}

				}
			}catch (IOException ioe){
				System.out.println("Erro na abertura do arquivo: " + ioe.getMessage());
			}

		}

	}
