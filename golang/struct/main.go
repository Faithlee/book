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

	fmt.Println("6.构造函数")
	whiteCat := NewCatByColor("white")
	fmt.Println("white cat:", whiteCat)
	blackCat := NewBlackCat("kitty")
	fmt.Println("父子关系的结构体构造:", blackCat)

	fmt.Println("7.类型内嵌/结构体内嵌")
	outer := new(outer)
	outer.b = 6
	outer.c = 7.5
	outer.int = 60
	outer.in1 = 5
	outer.in2 = 50
	fmt.Printf("outer.b=%d, outer.c=%f,outer.int=%d, outer.in1=%d, outer.in2=%d\n", outer.b, outer.c, outer.int, outer.in1, outer.in2)
	// 结构体字面量 todo outer is not a type
	//outer2 := outer{6, 7.5, 10, inner{5, 10}}
	//fmt.Println("outer2 is ", outer2)

	b := B{A{1, 2}, 3.0, 4.0}
	fmt.Println(b.ax, b.ay, b.bx, b.by)
	fmt.Println(b.A)

	fmt.Println("8.初始化内嵌结构体:")

	car := Car {
		Wheel: Wheel {
			Size: 18,
		},
		Engine: Engine{
			Type: "1.4T",
			Power: 130,
		},
	}
	fmt.Printf("car inner struct%+v\n",  car)

	secondCar := Car {
		Wheel: Wheel{
			Size: 18,
		},
		Engine: struct {
			Power int
			Type string
		}{
			Type: "1.4T",
			Power: 143,
		},
	}
	fmt.Printf("second car: %+v\n\n", secondCar)
	// todo 返回的不是地址？
	//fmt.Println(secondCar)

	fmt.Println("9.内嵌结构体成员命名冲突")
	class := & class{}
	class.classA.a = 1;
	fmt.Println("class.classA.a=", class)
	// 如果直接使用class.a = 1赋值，则会编译报错，成员命名冲突
	//class1 := & class{}
	//class1.a = 1;
	//fmt.Println("class.a=", class1, "\n")

	fmt.Println("10.单向链表")
	var head = new(Node)
	head.data = 1;

	var node1 = new(Node)
	node1.data = 2
	var node2 = new(Node)
	node2.data = 3

	head.next = node1
	node1.next = node2
	fmt.Println(" 遍历:")
	listNode(head)

	var head1 = new(Node)
	fmt.Println(" 头插法:")
	headInsert(head1)
	//listNode(head1)
	fmt.Println(" 尾插法:")
	tailInsert(head1)
	//listNode(head1)
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

// 创建和初始化结构体
type Cat struct {
	Name string
	Color string
}

func NewCatByName(name string) *Cat{
	return &Cat{
		Name: name,
	}
}
func NewCatByColor(color string) *Cat{
	return &Cat{
		Color: color,
	}
}

// 父子关系的结构体构造和初始化
type BlackCat struct {
	Cat
}
// 构造子类
func NewBlackCat(name string) *BlackCat {
	cat := &BlackCat{}
	cat.Name = name
	return cat
}

// 类型内嵌和结构内嵌
type inner struct {
	in1 int
	in2 int
}
type outer struct {
	b int
	c float32
	int	// anonymous
	inner	//anonymous
}

// 内嵌结构体
type A struct {
	ax, ay int
}
type B struct {
	A
	bx, by float32
}

// 内嵌结构体的初始化
type Wheel struct {
	Size int
}
type Engine struct {
	Power int
	Type string
}
type Car struct {
	Wheel
	Engine
}

// 匿名结构Engine
type secondCar struct{
	Wheel
	Engine struct {
		Power int
		Type string
	}
}

// 内嵌结构体成员名字冲突
type classA struct{
	a int
}
type classB struct {
	a int
}
type class struct {
	classA
	classB
}

// 单链表
type Node struct {
	data int
	next *Node
}
// 遍历单向链表
func listNode(node *Node) {
	for node != nil {
		fmt.Println(*node)
		node = node.next
	}
}

func headInsert(head *Node) {
	var tail *Node
	tail = head
	for i := 1; i < 10; i++ {
		var node = Node{data:i}
		node.next = tail
		tail = &node
	}

	listNode(tail)
}

func tailInsert(head *Node) {
	tail := head
	for i := 1; i < 10; i++ {
		var node = Node{data:i}
		tail.next = &node
		//(*tail).next = &node // todo 用法?
		tail = &node
	}
	listNode(head)
}
