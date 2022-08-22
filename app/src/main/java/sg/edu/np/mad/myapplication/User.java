package sg.edu.np.mad.myapplication;

public class User {
    private String name;
    public String getName(){ return name; };
    public void setName(String newName){ this.name = newName; };

    private String description;
    public String getDescription(){ return description; };
    public void setDescription(String newDescription){ this.description = newDescription; };

    private int id;
    public int getId(){ return id; };
    public void setId(int newId){ this.id = newId; };

    private boolean followed;
    public boolean getFollowed(){ return followed; };
    public void setFollowed(boolean newFollowed){ this.followed = newFollowed; };

    public User(String n, String d, int i, boolean f){
        this.name = n;
        this.description = d;
        this.id = i;
        this.followed = f;
    }

    public void Change(){
        if(followed == true){
            followed = false;
        }
        else{
            followed = true;
        }
    }
}