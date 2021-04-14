import java.util.Scanner;

public class Knapsack {
    private int weight;
    private int price;
    private int cost;

    public Knapsack(int w, int p) {
        super();

        this.weight = w;
        this.price = p;
        this.cost = (p/w);
    }

    public int GetWeight() {
        return weight;
    }

    public int GetPrice() {
        return price;
    }

    public int GetCost() {
        return cost;
    }

    public static Knapsack[] sort(Knapsack [] kp) {
        int max = 0;
        Knapsack change = new Knapsack(0, 0);

        for (int i = 0; i < kp.length - 1; i++) {
            max = kp[i].cost;
            for (int j = i + 1; j < kp.length; j++) {
                if (max < kp[j].cost) {
                    change.weight = kp[i].weight;
                    change.price = kp[i].price;
                    change.cost = kp[i].cost;

                    kp[i].weight = kp[j].weight;
                    kp[i].price = kp[j].price;
                    kp[i].cost = kp[j].cost;

                    kp[j].weight = change.weight;
                    kp[i].price = change.price;
                    kp[i].cost = change.cost;

                    max = kp[j].cost;
                }
            }
        }
        return kp;
    }

    public static void KnapsackPerform(int w[], int p[],int c, int size) {
        Knapsack [] kp = new Knapsack[size];

        for(int i = 0; i<kp.length;i++) {
            kp[i] = new Knapsack(w[i], p[i]); //총 금액, 량, 단위 당 금액을 가지고 있는 클래스형 배열 생성
        }

        kp = sort(kp); //단위 당 금액을 기준으로 배열 정렬 시키는 함수

        Knapsack [] result = new Knapsack[size];

        int current_w = 0;
        int current_v = 0;

        for(int i = 0; i < kp.length; i++) {
            if(c >= current_w + kp[i].weight) {
                current_w += kp[i].GetWeight();
                current_v += kp[i].GetPrice();
                result[i].weight = kp[i].GetWeight();
                result[i].price = kp[i].GetPrice();
            }
            else if(c - current_w > 0) {
                current_w += c - current_w;
                current_v += (c - current_w)*kp[i].GetCost();
                result[i].weight = (c - current_w);
                result[i].price = (c - current_w)*kp[i].GetCost();
            }
        }

        System.out.println("최종 weight 와 price : " + current_w + " , " + current_v);
        for(int i = 0; i < result.length; i++) {
            System.out.println( i + " " + result[i].weight + " " + result[i].price);
        }
    }

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("총 대상의 개수 : ");
        int number = scanner.nextInt();

        int arr_w[] = new int [number];
        int arr_p[] = new int [number];

        System.out.println("대상들의 weight : ");

        for(int i = 0; i < number; i++) {
            arr_w[i] = scanner.nextInt();
        }

        System.out.println("대상들의 price : ");
        for(int i = 0; i < number; i++) {
            arr_p[i] = scanner.nextInt();
        }

        System.out.println("수용량 입력 : ");
        int capacity = scanner.nextInt();

        KnapsackPerform(arr_w,arr_p,capacity, number);
    }
}
