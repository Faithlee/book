package main

import (
	"fmt"
	"sync"
	"time"
	"math/rand"
)

var wg sync.WaitGroup

func main() {
	court := make(chan int)

	wg.Add(2)

	go play("Nadal", court)
	go play("Djokovic", court)

	court <- 1

	wg.Wait()
}

func init() {
	rand.Seed(time.Now().UnixNano())
}

func play(player string, court chan int) {
	defer wg.Done()

	for {
		ball, ok := <-court
		if !ok {
			fmt.Printf("Player %s won!\n", player)
			return
		}

		n := rand.Intn(100)
		if n % 13 == 0 {
			fmt.Printf("Player %s Missed\n", player)
			close(court)
			return
		}

		fmt.Printf("Player %s hit %d\n", player, ball)
		ball++

		// 将球打向对手
		court<-ball
	}
}
