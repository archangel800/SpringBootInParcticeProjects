package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        FileService.readFile().subscribe(item -> System.out.println(item));
        FileService.writeFile("Giorgi magari kacia mas arunda qebao").subscribe();
        FileService.readFile().subscribe(item -> System.out.println(item));
        FileService.deleteFIle().subscribe();

    }
}
