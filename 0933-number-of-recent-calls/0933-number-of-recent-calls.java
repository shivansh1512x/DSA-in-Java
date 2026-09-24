class RecentCounter {

int[] queue= new int[10000];
int front=0;
int rear=0;

    public RecentCounter() {
    }
        public int ping(int t){
            queue[rear]=t;
            rear++;

            while(queue[front]<t-3000){
                front++;
            }
            return rear-front;
        }

    }

