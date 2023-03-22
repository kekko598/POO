package poo.esercizio28_02_2017;

public class Persona implements Comparable {
    private String NickName, Phone;

    public Persona( String nickName, String phone ) {
        NickName = nickName;
        Phone = phone;
    }

    public String getNickName() {
        return NickName;
    }

    public String getPhone() {
        return Phone;
    }

    @Override
    public int compareTo( Object o ) {
        Persona p = (Persona) o;
        return NickName.compareTo(p.NickName);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Persona{");
        sb.append("NickName='").append(NickName).append('\'');
        sb.append(", Phone='").append(Phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Phone.equals(persona.Phone) && NickName.equals(persona.NickName);
    }

    @Override
    public int hashCode() {
        int result = NickName.hashCode();
        result = 31 * result + Phone.hashCode();
        return result;
    }
}
