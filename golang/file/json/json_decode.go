package main

import (
	"fmt"
	"os"
	"encoding/json"
)

type Website struct {
	Name	string `xml:"name,attr"`
	Url		string
	Course	[]string
}

func main() {
	filePtr, err := os.Open("../data/info.json")
	if err != nil {
		fmt.Println("文件打开失败:", err.Error())
	}
	defer filePtr.Close()

	var info []Website

	decoder := json.NewDecoder(filePtr)
	err = decoder.Decode(&info)
	if err != nil {
		fmt.Println("解码失败:", err.Error())
	} else {
		fmt.Println("解码成功", info)
	}
}
