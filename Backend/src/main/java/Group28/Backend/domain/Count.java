package Group28.Backend.domain;

public class Count
{
  private int count;
  private String name;

  public Count(String name, int count)
  {
    this.name = name;
    this.count = count;
  }

  public int getCount()
  {
    return count;
  }

  public String getName()
  {
    return name;
  }

  public void setCount(int count)
  {
    this.count = count;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
