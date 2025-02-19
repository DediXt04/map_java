package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Program {

    public static void main(String[] args) {

        Map<String, Integer> urna = new HashMap<>();  // Mapa para armazenar candidatos e votos

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file full path: ");
        String path = sc.nextLine();
        // Exemplo de arquivo: c:\tempi\votos.txt

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                
                if (fields.length == 2) {  // Verificando se a linha tem 2 campos
                    String candidato = fields[0];
                    try {
                        int votos = Integer.parseInt(fields[1]);  // Tentando converter os votos para um número inteiro
                        
                        // Se o candidato já tem votos, soma os votos, caso contrário, inicializa com os votos atuais
                        urna.put(candidato, urna.getOrDefault(candidato, 0) + votos);  
                    } catch (NumberFormatException e) {
                        // Caso o valor de votos não seja válido, mostra um erro e ignora a linha
                        System.out.println("Erro no formato dos votos. Ignorando linha: " + line);
                    }
                } else {
                    // Caso a linha não tenha o formato esperado (2 campos), mostra um erro
                    System.out.println("Formato inválido na linha: " + line);
                }
                line = br.readLine();  // Lê a próxima linha
            }

            // Ordenando os candidatos pelos votos em ordem decrescente
            List<Map.Entry<String, Integer>> listaOrdenada = new ArrayList<>(urna.entrySet());
            
            // Ordenando usando Comparator para os valores (votos) em ordem decrescente
            listaOrdenada.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Exibindo o resultado ordenado
            System.out.println("\nResultado final:");
            for (Map.Entry<String, Integer> entry : listaOrdenada) {
                System.out.println(entry.getKey() + ": " + entry.getValue());  // Mostra o total de votos por candidato
            }

        } catch (IOException e) {
            // Se houver um erro ao ler o arquivo, mostra uma mensagem de erro
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        sc.close();
    }
}
