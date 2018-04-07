package Generic09;

/**
 * 泛型类及接口
 */
public class GenericClassDemo {

    public static void main(String[] args)
    {
        // todo 泛型声明的Apple类实例化，创建多个逻辑子类
        // todo 注意：并不存在泛型类，因此静态变量、静态初始化块或静态变量的声明和初始化都不允许使用类型形参
        Apple<String> apple1 = new Apple<>("苹果");
        System.out.println(apple1.getInfo());
        Apple<Double> apple4 = new Apple<>(5.00);
        System.out.println(apple4.getInfo());


        // 派生子类
        HuaNiu apple2 = new HuaNiu("花牛");
        System.out.println(apple2.getInfo());

        HongFuShi apple3 = new HongFuShi("红富士");
        System.out.println(apple3.getInfo());
    }
}

// todo 1、泛型类的定义：注意构造器不需要指定 泛型形参 (类型实参)

class Apple<T>
{
    protected T info;

    // 默认构造器
    public Apple() {}

    public Apple(T info)
    {
        this.info = info;
    }

    public void setInfo(T info)
    {
        this.info = info;
    }

    public T getInfo()
    {
        return this.info;
    }
}

// todo 2、泛型类派生子类 定义与使用
// todo 定义接口、类、方法时使用使用 类型形参
// todo 使用时接口、类、方法应该为 类型形参 传入 实际类型
// (注意：类与接口的使用可以不传入 实际类型，如HuaNiu类即没有指定实际类型，但会产生未检查的警告)；
class HuaNiu extends Apple
{
    public HuaNiu(String info)
    {
        this.info = info;
    }

    public String getInfo()
    {
        return super.getInfo().toString();
    }
}

// todo 注意：使用方法时不能再包含类型形参，应该使用 实际类型
// 派生子类指定实际类型后，子类所有使用T类型形参的地方都将替换为String类型；
// 使用Apple类时为T形参传入String类型，则所有的使用到形参的地方都将被替换为String，如下HongFuShi类
class HongFuShi extends Apple<String>
{
   public HongFuShi(String info)
   {
        this.info = info;
   }

    /**
     * todo 重写getInfo及返回类型必须与声明<String>的一样
     *
     * @return
     */
    public String getInfo()
    {
        return  "子类" + super.getInfo();
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
