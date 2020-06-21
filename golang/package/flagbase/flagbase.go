package flagbase

import "fmt"
import "flag"
import "time"
import "strings"

func BaseType() {
	fmt.Println("  命令行解析:flag.Type方式")
	name := flag.String("name", "张三","姓名")
	age  := flag.Int("age", 18, "年龄")
	married := flag.Bool("married", false, "婚否")
	delay := flag.Duration("delay", 0, "时间间隔")

	flag.Parse()

	fmt.Println("	name=", *name)
	fmt.Println("	age=", *age)
	fmt.Println("	married=", *married)
	fmt.Println("	delay=", *delay)

	fmt.Println()
}

func BaseTypeVar() {
	fmt.Println("  命令行解析:flag.TypeVar方式")
	// go run package/main -name=Wang -age=20 -married=T -d=1s
	var name string
	var age  int
	var married bool
	var delay time.Duration

	flag.StringVar(&name, "name", "张三", "姓名")
	flag.IntVar(&age, "age", 18, "年龄")
	flag.BoolVar(&married, "married", false, "婚否")
	flag.DurationVar(&delay, "d", 0, "时间间隔")

	flag.Parse()

	fmt.Println("	name=", name)
	fmt.Println("	age=", age)
	fmt.Println("	married=", married)
	fmt.Println("	delay=", delay)

	fmt.Println()
}

func Args() {
	fmt.Println("  查看参数个数:")

	var name = flag.String("name", "gerry", "input your name")
	var age  = flag.Int("age", 20, "input your age")

	var flagvar int
	flag.IntVar(&flagvar, "flagname", 1000, "help message for flagname")

	flag.Parse()

	// go run package/main -name=Wang -age=20 -flagname=500  111 111bbb
	fmt.Printf("	参数个数:args=%s, num=%d\n", flag.Args(), flag.NArg())
	for i := 0; i < flag.NArg(); i++ {
		fmt.Printf("	arg[%d]=%s\n", i, flag.Arg(i))
	}
	fmt.Println()

	fmt.Printf("	flag args num: %d\n", flag.NFlag())
	fmt.Println("	name=", *name)
	fmt.Println("	age=", *age)
	fmt.Println("	flagname=", flagvar)
}

type sliceValue []string

func newSliceValue(vals []string, p *[]string) *sliceValue {
	*p = vals
	return (*sliceValue)(p)
}

// 实现flag包中的value接口
func (s *sliceValue) Set(val string) error {
	*s = sliceValue(strings.Split(val, ","))
	return nil
}
func (s *sliceValue) String() string {
	*s = sliceValue(strings.Split("default is me", ","))
	return "it's none of my business"
}


func Define() {
	fmt.Println("  自定义命令行参数:")

	var languages []string
	flag.Var(newSliceValue([]string{}, &languages), "slice", "I like programming `languages`")

	flag.Parse()

	fmt.Println("	", languages)
}
