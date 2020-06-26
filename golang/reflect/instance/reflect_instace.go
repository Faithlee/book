package main

import (
	"fmt"
	"reflect"
)

func main() {
	var a int

	// 通过类型信息创建实例
	typeOfA := reflect.TypeOf(a)
	instance := reflect.New(typeOfA)
	fmt.Println(instance.Type(), instance.Kind())
	fmt.Println()

	// 通过反射调用函数
	funcValue := reflect.ValueOf(sum)

	paramList := []reflect.Value{
		reflect.ValueOf(10),
		reflect.ValueOf(20),
	}

	retList := funcValue.Call(paramList)

	fmt.Println(retList[0].Int())
	fmt.Println(retList[0].Interface())
}

func sum(a, b int) int {
	return a+b
}
