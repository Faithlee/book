package main

import (
	"fmt"
	"os"
	"encoding/gob"
)

func main() {
	data := map[string]string{
		"name":	"golang",
		"desc": "very good",
	}

	file, err := os.OpenFile("../data/golang.gob", os.O_RDWR|os.O_CREATE, 0777)
	if err != nil {
		fmt.Println("创建文件失败:", err)
		return
	}
	defer file.Close()

	encoder := gob.NewEncoder(file)
	if err := encoder.Encode(data); err != nil {
		fmt.Println("保存gob文件失败:", err)
		return
	}

	fmt.Println("保存成功")
}
