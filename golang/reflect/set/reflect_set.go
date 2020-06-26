package main

import (
	"fmt"
	"reflect"
)

// 反射值可修改的条件:
// - 可被寻址
// - 可导出
func main() {
	// 可寻址
	var a int = 1024
	fmt.Println("old a:", a)

	v := reflect.ValueOf(&a)
	v = v.Elem()
	v.SetInt(2048)

	fmt.Println("new a:", a)
	fmt.Println(v.Int())
	fmt.Println()

	// 可导出
	type Dog struct {
		LegCount int
	}
	dog := &Dog{}

	valueOfDog := reflect.ValueOf(dog)
	valueOfDog = valueOfDog.Elem()

	count := valueOfDog.FieldByName("LegCount")
	count.SetInt(4)

	fmt.Println("dog has legs:", count.Int())
	fmt.Println("dog has legs:", dog.LegCount)
}
