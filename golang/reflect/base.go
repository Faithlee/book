package main

import (
	"fmt"
	"reflect"
)

func main() {
	// 对比Type与Kind
	var  a int
	typeOfA := reflect.TypeOf(a)
	fmt.Println(typeOfA.Name(), typeOfA.Kind())

	type MyInt int
	var b MyInt
	typeOfB := reflect.TypeOf(b)
	fmt.Println(typeOfB.Name(), typeOfB.Kind())
	fmt.Println()

	// 反射常量
	type Enum int
	const (
		Zero Enum = 0
	)
	typeOfC := reflect.TypeOf(Zero)
	fmt.Printf("常量Type: %s, Kind: %s\n", typeOfC.Name(), typeOfC.Kind())

	// 反射结构体
	type cat struct {}
	typeOfD := reflect.TypeOf(cat{})
	fmt.Println("结构体Type:", typeOfD.Name(), " Kind:", typeOfD.Kind())

	//var e cat = cat{}
	//var e = cat{}
	e := cat{}
	typeOfE := reflect.TypeOf(e)
	fmt.Println("结构体Type:", typeOfE.Name(), " Kind:", typeOfE.Kind())

	// 指针及指针指向的元素
	ins := &cat{}
	typeOfF := reflect.TypeOf(ins)
	fmt.Printf("指针Type: %v, Kind: %v \n", typeOfF.Name(), typeOfF.Kind())

	typeOfG := typeOfF.Elem()
	fmt.Printf("指针指向元素Type: %v, Kind: %v\n", typeOfG.Name(), typeOfF.Kind())
}
