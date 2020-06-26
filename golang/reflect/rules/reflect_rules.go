package main

import (
	"fmt"
	"reflect"
)

// 接口变量转换为反射类型对象
func main() {
	var x float64 = 3.14

	fmt.Println("value=", reflect.ValueOf(x))

	v := reflect.ValueOf(x)
	fmt.Println("type:", v.Type())
	fmt.Println("Kind is float64：", v.Kind() == reflect.Float64)
	fmt.Println("value=", v.Float())
	fmt.Println()

	var y uint8 = 'y'
	yv := reflect.ValueOf(y)
	fmt.Println("type:", yv.Type())
	fmt.Println("Kind is uint8:", yv.Kind() == reflect.Uint8)
	// yv.Uint retrun uint64
	y = uint8(yv.Uint())
	fmt.Println(y)

	fmt.Println(yv.Interface())
}
