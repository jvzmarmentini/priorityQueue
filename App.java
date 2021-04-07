import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * App
 */
public class App {
    static Scanner sc = new Scanner(System.in);
    private static final int QNTTAREFAS = 20;
    private static PriorityQueue<Tarefa> queue = new PriorityQueue<>(QNTTAREFAS);

    // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    // CSV file header
    private static final String FILE_HEADER = "nome,prioridade";

    // File name
    private static final String FILE_NAME = "tarefas.csv";

    // Student attributes index
    private static final int TAREFA_NOME_IDX = 0;
    private static final int TAREFA_PRIORIDADE_IDX = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("1: cadastrar nova tarefa");
            System.out.println("2: pesquisar tarefa mais prioritaria");
            System.out.println("3: excluir todas tarefas");
            System.out.println("4: listar tarefas pendentes");
            System.out.println("5: exportar para csv");
            System.out.println("6: importar o csv");
            System.out.println("7: sair");
            System.out.print(": ");

            int opc = sc.nextInt();
            System.out.println();

            switch (opc) {
            case 1:
                cadastrar();
                break;
            case 2:
                maisPrioritaria();
                break;
            case 3:
                limpar();
                break;
            case 4:
                listarTodos();
                break;
            case 5:
                exportarCsv();
                break;
            case 6:
                importarCsv();
                break;
            case 7:
                if (!queue.isEmpty()) {
                    System.out.print("ainda existem tarefas pendentes. deseja sair? [y/n]: ");
                    sc.nextLine();
                    if (sc.nextLine().toLowerCase().equals("y")) {
                        return;
                    } else {
                        break;
                    }
                } else {
                    return;
                }
            default:
                System.out.println();
                System.out.println("opcao invalida");
                break;
            }
        }
    }

    private static void importarCsv() {
        BufferedReader fileReader = null;

        try {
            String line = "";

            // Create the file reader
            fileReader = new BufferedReader(new FileReader(FILE_NAME));

            // Read the CSV file header to skip it
            fileReader.readLine();

            // Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    // Create a new student object and fill his data
                    Tarefa tarefa = new Tarefa(tokens[TAREFA_NOME_IDX],
                            Integer.parseInt(tokens[TAREFA_PRIORIDADE_IDX]));
                    queue.add(tarefa);
                }
            }

            System.out.println("importado com sucesso!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
    }

    private static void exportarCsv() {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(FILE_NAME, false);

            // Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            // Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write a new student object list to the CSV file
            for (Tarefa tarefa : queue) {
                fileWriter.append(tarefa.getNome());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(tarefa.getPrioridade()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            System.out.println("exportado com sucesso!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    private static void listarTodos() {
        if (!queue.isEmpty()) {
            for (Tarefa tarefa : queue) {
                System.out.println(tarefa);
            }
        } else {
            System.out.println("nenhuma tarefa cadastrada!");
        }

    }

    private static void limpar() {
        queue.clear();
        System.out.println("todas tarefas excluidas!");
    }

    private static void maisPrioritaria() {
        if (!queue.isEmpty()) {
            System.out.println("tarefa mais prioritaria: " + queue.peek());
        } else {
            System.out.println("nenhuma tarefa cadastrada!");
        }
    }

    private static void cadastrar() {
        System.out.print("digite o nome: ");
        String nome = sc.nextLine();
        nome = sc.nextLine();
        System.out.print("digite a prioridade: ");
        int prio = sc.nextInt();
        Tarefa t = new Tarefa(nome, prio);
        queue.add(t);
    }
}
