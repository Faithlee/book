package main

import "fmt"
import "io"
import "sort"

func main() {
	fmt.Println("接口")

	var dataWriter DataWriter

	f := new(file)
	dataWriter = f

	dataWriter.WriteData("data")

	var writer DataWriter
	writer = new(file)
	writer.WriteData("database")

	writer = new(Database)
	writer.WriteData("database")
	fmt.Println()

	fmt.Println("2.接口的实现:")
	// 一个类型实现多个接口:Writer/Closer
	s := new(Socket)
	useWriter(s)
	useCloser(s)
	// 多个类型实现相同接口
	var service Service
	service = new(GameService)
	service.Start()
	service.Log("hello")
	fmt.Println()

	fmt.Println("3.类型断言")
	var x interface{}
	x = 10
	value,ok := x.(int)
	fmt.Print("x=", value, ",ok=", ok, "\n")

	// nil类型?
	var n interface{}
	n = nil
	value1, ok := n.(int)
	fmt.Println(value1, ok)
	fmt.Println()

	fmt.Println("go语言排序:")
	games := MyStringList{
		"3. Triple kill",
		"5. Penta kill",
		"2. Double kill",
		"4. Quadra kill",
		"1. First Blood",
	}
	sort.Sort(games)
	for _, v := range games {
		fmt.Printf("%s\n", v)
	}
	// 直接使用StringSlice
	var names sort.StringSlice
	names = sort.StringSlice{"one", "two", "three", "four"}
	sort.Strings(names)
	fmt.Println("字符串直接排序:", names)

	// 对整数排序
	nums := sort.IntSlice{8,6,4,9,3,5,1,}
	sort.Ints(nums)
	fmt.Println("整数直接排序:", nums)

	// 结构体排序
	heros := Heros{
		&Hero{"吕布", Tank,},
		&Hero{"李白", Assassin,},
		&Hero{"妲己", Mage},
		&Hero{"貂蝉", Assassin,},
		&Hero{"关羽", Tank},
		&Hero{"诸葛亮", Mage},
	}
	sort.Sort(heros)
	for _,v := range heros {
		fmt.Printf("%+v\n", v)
	}
}

type DataWriter interface {
	WriteData(data interface{}) error
}

type file struct {

}

type Database struct {

}

func (d *file) WriteData(data interface{}) error {
	fmt.Println("writeData:", data)
	return nil
}

func (database Database) WriteData(data interface{}) error {
	fmt.Println("database Writer", data)
	return nil
}

// #接口的实现
type Socket struct {

}
func (s Socket)Write(p []byte) (n int, err error) {
	return 0,nil
}
func (s Socket)Close() error {
	return nil
}

func useWriter(writer io.Writer) {
	writer.Write(nil)
}
func useCloser(closer io.Closer) {
	closer.Close()
}


// # 接口的实现
type Service interface {
	Start()
	Log(string)
}

type Logger struct {

}
func (logger Logger) Log(str string) {
	fmt.Println("service log:", str)
}

type GameService struct {
	Logger
}

func (game GameService) Start() {
	fmt.Println("service start")
}

// #排序
type MyStringList []string

func (m MyStringList) Len() int {
	return len(m)
}
func (m MyStringList) Less(i, j int) bool {
	return m[i] < m[j]
}
func (m MyStringList) Swap(i, j int) {
	m[i],  m[j] = m[j], m[i]
}

// 结构体排序：需要明确排序的维度
type HeroKind int
const (
	None HeroKind = iota
	Tank
	Assassin
	Mage
)

type Hero struct {
	Name string
	Kind HeroKind
}

type Heros []*Hero
func (heros Heros) Len() int {
	return len(heros)
}
func (heros Heros) Less(i, j int) bool{
	if  heros[i].Kind != heros[j].Kind {
		return heros[i].Kind < heros[j].Kind
	}

	return heros[i].Name < heros[j].Name
}
func (heros Heros) Swap(i, j int) {
	heros[i], heros[j] = heros[j], heros[i]
}
