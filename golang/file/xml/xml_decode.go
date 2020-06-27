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
	file, err := os.Open("../data/golang.xml")
	if err != nil {
		fmt.Printf("文件打开失败:%v", err)
		return
	}
	defer file.Close()

	info := &Website{}
	decoder := xml.NewDecoder(file)
	err = decoder.Decode(info)
	if err != nil {
		fmt.Printf("xml解析失败:%v", err)
		return
	}

	fmt.Println("xml解析成功:", info)
}
