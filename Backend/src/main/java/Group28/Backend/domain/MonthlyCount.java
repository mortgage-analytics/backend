package Group28.Backend.domain;

public class MonthlyCount
{
    private String name;
    private int applications;
    private int leads;

    public MonthlyCount(String name, int leads, int applications)
    {
        this.name = name;
        this.applications = applications;
        this.leads = leads;
    }

    // Setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setApplications(int applications)
    {
        this.applications = applications;
    }

    public void setLeads(int leads)
    {
        this.leads = leads;
    }

    // Getters
    public String getName()
    {
        return name;
    }

    public int getApplications()
    {
        return applications;
    }

    public int getLeads()
    {
        return leads;
    }
}
