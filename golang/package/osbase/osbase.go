package osbase

import "fmt"
import "log"
import "os"
import "os/exec"
import "os/user"
import "os/signal"

// 常用函数
func Base() {
	fmt.Println("  常用函数:")
	hostname, _ := os.Hostname()
	fmt.Println("	", hostname)
	//fmt.Println("	", os.Environ())
	fmt.Println("	uid,gid,pid:", os.Getuid(), os.Getgid(), os.Getpid())
	fmt.Println()
}

// os/exec子包
func Exec() {
	fmt.Println("  os/exec子包执行外命令：")
	// 在PATH路径中搜索ls命令
	f, err := exec.LookPath("ls")
	if err != nil {
		fmt.Println("	", err)
	}
	fmt.Println("	", f)

	fmt.Println()
}

func User() {
	fmt.Println("  os/user子包:")
	user, _ := user.Current()
	fmt.Printf("	用户名: %s, 用户id: %d, 用户主目录: %s, 主组id: %s\n", user.Username, user.Uid, user.HomeDir, user.Gid)
	//log.Printf("	用户名: %s, 用户id: %d, 用户主目录: %s, 主组id: %d\n", user.Username, user.Uid, user.HomeDir, user.Gid)

	s, _ := user.GroupIds()
	log.Println("用户所有组:", s)

	fmt.Println()
}

// 信息处理
func Signal() {
	fmt.Println("  os/signal信号处理:")
	c := make(chan os.Signal, 0)
	signal.Notify(c)

	signal.Stop(c)
	s := <-c
	fmt.Println("	got signal:", s)
}
