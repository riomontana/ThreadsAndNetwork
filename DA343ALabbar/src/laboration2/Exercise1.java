package laboration2;
import java.util.*; // ArrayList
import java.io.*; // BufferedReader, FileReader, IOException

public class Exercise1 {
    public static ArrayList<Person> readPersons( String filename ) {
        ArrayList<Person> list = new ArrayList<Person>();
        try {
            BufferedReader br = new BufferedReader( new FileReader( filename ) );
            String[] parts;
            Person person;
            String str = br.readLine();
            while( str != null ) {
                parts = str.split( "," );
                person = new Person( parts[ 0 ], parts[ 1 ], parts[ 2 ] );
                list.add( person );
                str = br.readLine();
            }
            br.close();
        } catch( IOException e ) {
            System.out.println( "readPersons: " + e );
        }
        return list;
    }
    
    public static void main( String[] args ) {
        ArrayList<Person> list = Exercise1.readPersons( "files/personer.txt" );
        Person person1 = new Person( "761201-7654", "Henry", "Smith" );
        Person person2 = new Person( "011003-4444", "Alma", "Björk" );
        Person p;
        int a;
        
        System.out.println( list.toString() ); // A skriver ut listan med personer
        
        p = list.get( 2 ); // B hämtar person 2 ur listan
        System.out.println( p.toString() ); 
        
        list.add( person1 ); // C lägger till person i listan
        System.out.println( list.toString() );
        
        list.add( 4, person2 ); // D 
        System.out.println( list.toString() );
        
        a = list.size(); // E skriver ut storleken på listan
        System.out.println( a );
        
        a = list.indexOf( person2 ); // F skriver ut specifikt plats i listan
        System.out.println( a );
        
        if( list.contains( person1 ) ) { // G kollar om person1 finns i listan eller ej. skriver ut om den finns eller ej.
            System.out.println( person1.toString() + " FINNS" );
        } else {
            System.out.println( person1.toString() + " FINNS EJ" );
        }
        
        list.remove( 1 ); // H tar bort namn ur listan
        System.out.println( list.toString() );
        
        list.clear(); // I nollställer listan
        System.out.println( list.toString() );
    }
}
