package main

import (
	"fmt"
	"reflect"
)

// 修改反射类型对象，值必须是可写的
func main() {
	var x float64 = 3.14

	// 检查值是否可写
	v := reflect.ValueOf(x)
	fmt.Println("modify of v: ", v.CanSet())
	fmt.Println()

	// 检查指针是否可写
	p := reflect.ValueOf(&x)
	fmt.Println("type of p:", p.Type())
	fmt.Println("modify of p :", p.CanSet())
	fmt.Println()

	// 检查指针指向的数据是否可写：true
	pv := p.Elem()
	fmt.Println("modify of pv: ", pv.CanSet())
	fmt.Println()

	// 修改值
	pv.SetFloat(2.8)
	fmt.Println("反射类型对象转换接口类型的值: ", pv.Interface())
	fmt.Println("modify of x :", x)
	fmt.Println()

	// 用结构体地址创建反射变量并修改数据
	type T struct {
		A int
		B string
	}
	t := T{123, "java"}
	s := reflect.ValueOf(&t).Elem()
	typeOfT := s.Type()
	fmt.Println("type of s:", typeOfT)

	for i := 0; i < s.NumField(); i++ {
		f := s.Field(i)

		fmt.Printf("%d: %s %s = %v\n", i, typeOfT.Field(i).Name, f.Type(), f.Interface())
	}

	// 修改结构体
	fmt.Println("old t:", t)
	s.Field(0).SetInt(12)
	s.Field(1).SetString("golang")
	fmt.Println("new t:", t)
}
