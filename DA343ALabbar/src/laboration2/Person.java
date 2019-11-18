package laboration2;

public class Person implements Comparable<Person> {
    private String id;
    private String firstName;
    private String lastName;
    
    public Person( String id, String firstName, String lastName ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String toString() {
        return id + " " + firstName + " " + lastName;
    }    
    
    public boolean equals(Object obj) {
    	boolean res = false;
    	if(obj instanceof Person) {
    		Person p = (Person)obj;
    		res = p.id.equals(id);  		
    	}
    	return res;
    }
//    public boolean equals(Object o) {
//    	return (o instanceof Person && ((Person)o).id.equals(id));
//    	}
    
    public int compareTo(Person p) {
    	return id.compareTo(p.getId());
    }
}
