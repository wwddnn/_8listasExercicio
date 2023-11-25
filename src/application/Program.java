package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Employee> list = new ArrayList<>();
        System.out.print("How many employees will be registrered ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println();
            System.out.println("Employee #" + (i + 1));
            System.out.print("Id ");
            Integer id = sc.nextInt();
            while (hasId(list, id)){
                System.out.println("Id already taken. Try again ");
                id = sc.nextInt();
            }
            System.out.print("Name ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Salary ");
            Double salary = sc.nextDouble();
            //adiciona por padrao um elemento no fim da lista
            Employee emp = new Employee(id, name, salary);
            list.add(emp);
        }
        System.out.print("Enter the employee id that will have salary increase ");
        Integer idSalary = sc.nextInt();

        //Integer pos = position(list, idSalary);
        //outra forma de resolver a linha acima seria assim:
        //stream é um tipo especial em java que aceita funcoes lambdas.
        //a funcao filter vai filtrar a minha lista só os elementos que eu colocar no predicado para atender o que desejo.
        //lendo fica assim, filtra os meus funcionarios que tem o getid igual ao idSalary, e pega o primeiro deles, e se nao achar retorna nulo.
        //em outras palavras estou buscando na lista a ocorrencia do primeiro funcionario, se for igual ao que eu digitei.
        Employee emp = list.stream().filter(x -> x.getId() == idSalary).findFirst().orElse(null);

        if (emp == null) {
            System.out.println("This id does not exist ");
        } else {
            System.out.print("Enter the percentage ");
            double percent = sc.nextDouble();
            emp.increaseSalary(percent);
        }
        System.out.println();
        System.out.println("List of employee");
        for (Employee e : list) {
            System.out.println(emp);
        }

        sc.close();
    }
    //repara que se eu adotar o int no public, la no return pode ser -1. mas se eu usar public Integer como wrapper class, entao no final vai ser return null.
    public static Integer position(List<Employee> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
            return null;
        }
        return null;
    }
    //sobre o codigo acima, que é um metodo static, podemos fazer de outra forma que é usando uma funcao booleana com stream, filter, findFirst, e orElse. fica assim:
    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }

}