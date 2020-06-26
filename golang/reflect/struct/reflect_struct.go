package main

import (
	"fmt"
	"reflect"
)

// 反射遍历结构体成员
func main() {
	// 注意tag的写法是非常固定的，写错也不有报错
	type cat struct {
		Name string
		Type int `json:"type" id:"100"`
	}

	ins := cat{Name: "kitty", Type: 1,}

	typeOfCat := reflect.TypeOf(ins)
	//fmt.Println(typeOfCat.Name(), typeOfCat.Kind())

	// 遍历结构体成员
	for i := 0; i < typeOfCat.NumField(); i++ {
		fieldType := typeOfCat.Field(i)
		fmt.Printf("name: %v tag: '%v'\n", fieldType.Name, fieldType.Tag)
	}

	if catType, ok := typeOfCat.FieldByName("Type"); ok {
		fmt.Println(catType.Tag.Get("json"))
		// 多值fmt会对其拆包
		fmt.Println(catType.Tag.Lookup("id"))
	}
}
