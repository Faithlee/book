package main

import (
	"fmt"
	"os"
	"encoding/xml"
)

type Website struct {
	Name	string `xml:"name,attr"`
	Url		string
	Course	[]string
}

func main() {
	info := Website{
		"golang",
		"https://golang.org/",
		[]string{"go并发"},
	}

	filePtr, err := os.Create("../data/golang.xml")
	if err != nil {
		fmt.Println("文件创建失败", err.Error())
		return
	}
	defer filePtr.Close()

	encoder := xml.NewEncoder(filePtr)
	err = encoder.Encode(info)
	if err != nil {
		fmt.Println("编码错误:", err.Error())
	} else {
		fmt.Println("编码成功")
	}
}
