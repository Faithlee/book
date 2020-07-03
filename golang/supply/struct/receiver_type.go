package main

import (
	"fmt"
)


func main() {
	var b1 B
	b1.change()
	fmt.Println(b1.write())

	b2 := new(B)
	b2.change()
	fmt.Println(b2.write())

	// 使用值作为接收者没有改变参数值
	var b3 B
	b3.modify()
	fmt.Println(b3.write())
}

type B struct {
	thing int
}

func (b *B) change() {
	b.thing = 1
}

func (b B) modify() {
	b.thing = 2
}

func (b B) write() string {
	return fmt.Sprint(b)
}
