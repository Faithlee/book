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
	info := []Website{
		{
			"golang",
			"http://www.google.com",
			[]string{"golang", "concurrency",},
		},
		{
			"objective-c",
			"http://www.apple.com",
			[]string{"objective-c", "Andriod",},
		},
	}

	filePtr, err := os.Create("../data/info.json")
	if err != nil {
		fmt.Println("文件创建失败", err.Error())
		return
	}
	defer filePtr.Close()

	encoder := json.NewEncoder(filePtr)
	err = encoder.Encode(info)
	if err != nil {
		fmt.Println("编码失败", err.Error())
	} else {
		fmt.Println("编码成功")
	}
}
