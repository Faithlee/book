package main

import "fmt"

type Point struct {
	X int
	Y int
}

type Color struct {
	R,G,B int
}

type Command struct {
	name string		//名称
	version *int	//绑定变量
	comment string	//注释
}

type People struct {
	name string 
	child *People
}

type Address struct {
	provice string
	city	string
	zipcode int
	phone	string
}

func main() {
	fmt.Println("1.结构体实体化基本操作:")
	var point Point
	point.X = 100
	point.Y = 100
	fmt.Println("point:", point)

	fmt.Println("2.创建指针类型的结构体:")
	p1 := new(Point)
	p1.X = 10
	p1.Y = 10
	fmt.Println("指针类型结构体p1:" , p1, "\n")

	fmt.Println("3.取结构体地址实例化:")
	var version int = 1
	c1 := &Command{}
	c1.name = "version1"
	c1.version = &version
	c1.comment = "show version"
	fmt.Println("结构体地址实例c1:", c1)

	version = 2
	c2 := newCommand("version2", &version, "show version")
	fmt.Println("结构体地址实例c2:", c2)

	c3 := &Command{
		name: "version3",
		version: &version,
		comment: "show version",
	}
	fmt.Println("结构体地址实例c3:", c3, "\n")

	fmt.Println("4.结构体初始化")
	// 键值对初始化
	relation := &People{
		name: "grandfather",
		child: &People{
			name: "father",
			child: &People{
				name: "Bob",
			},
		},
	}
	fmt.Println("键值对初始化:", relation)

	// 多个值列表初始化
	addr := Address{
		"北京",
		"朝阳",
		10010,
		"",
	}
	fmt.Println("多个值列表初始化:", addr, "\n")

	fmt.Println("5.匿名结构体")
	msg := &struct{
		id int
		data string
	}{
		1024,
		"hello",
	}
	printMsg(msg)
}

func newCommand(name string, version *int, comment string) *Command {
	return &Command {
		name: name,
		version: version,
		comment: comment,
	}
}

func printMsg(msg *struct{
	id int
	data string
}) {
	fmt.Printf("%T\n", msg)
	fmt.Println("匿名结构体:", msg, "\n")
}
