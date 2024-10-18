package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //firstTask();

        //PrimesGeneratorTest.test();

        //thirdTask();

        //fourthTask();

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("One", 1);
        originalMap.put("Two", 2);
        originalMap.put("Three", 3);
        originalMap.put("Four", 1);

        Map<Integer, List<String>> reversedMap = fifthTask(originalMap);

        System.out.println("Original map: " + originalMap);
        System.out.println("Reversed map: " + reversedMap);
    }

    // Задание 1
    public static void firstTask() {

        // a)
        int N = 100;
        Random random = new Random();
        Integer[] array = new Integer[N];
        for (int index = 0; index < N; ++index) {
            array[index] = random.nextInt(101);
        }
        System.out.println("Array: " + Arrays.toString(array));

        // b)
        List<Integer> list = new ArrayList<>(Arrays.asList(array));
        System.out.println("List: " + list);

        // c)
        Collections.sort(list);
        System.out.println("Sorted top list: " + list);

        // d)
        Collections.sort(list, Collections.reverseOrder());
        System.out.println("Sorted reverse list: " + list);

        // e)
        Collections.shuffle(list);
        System.out.println("Shuffled list: " + list);

        // f)
        Collections.rotate(list, 1);
        System.out.println("Rotated list: " + list);

        // g)
        Set<Integer> uniqueSet = new HashSet<>(list);
        List<Integer> uniqueList = new ArrayList<>(uniqueSet);
        System.out.println("Unique list: " + uniqueList);

        // h)
        List<Integer> duplicateList = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (Integer num : list) {
            if (!seen.add(num)) {
                duplicateList.add(num);
            }
        }
        System.out.println("Duplicate list: " + duplicateList);

        // i)
        Integer[] newArray = list.toArray(new Integer[0]);
        System.out.println("New array: " + Arrays.toString(newArray));

        // j)
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Integer num : newArray) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        System.out.println("Frequency of numbers: " + frequencyMap);
    }

    // Задание 2
    static class PrimesGenerator implements Iterable<Integer> {
        private final List<Integer> primes;

        public PrimesGenerator(int N) {
            primes = new ArrayList<>();
            generatePrimes(N);
        }

        private void generatePrimes(int N) {
            int number = 2;
            while (primes.size() < N) {
                if (isPrime(number)) {
                    primes.add(number);
                }
                ++number;
            }
        }

        private boolean isPrime(int number) {
            if (number < 2) return false;
            for (int divider = 2; divider <= Math.sqrt(number); ++divider) {
                if (number % divider == 0) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public Iterator<Integer> iterator() {
            return primes.iterator();
        }

        public Iterator<Integer> reverseIterator() {
            return new Iterator<>() {
                private int currentIndex = primes.size() - 1;

                @Override
                public boolean hasNext() {
                    return currentIndex > 0;
                }

                @Override
                public Integer next() {
                    return primes.get(--currentIndex);
                }
            };
        }
    }

    static class PrimesGeneratorTest {
        public static void test() {
            int N = 10;
            PrimesGenerator primesGenerator = new PrimesGenerator(N);

            System.out.println("First " + N + " prime numbers:");
            for (int primeNumber : primesGenerator) {
                System.out.print(primeNumber + " ");
            }

            System.out.println("\nFirst " + N + " prime numbers in reversed direction:");
            Iterator<Integer> reverseIterator = primesGenerator.reverseIterator();
            while (reverseIterator.hasNext()) {
                System.out.println(reverseIterator.next() + " ");
            }
        }
    }

    // Задание 3
    static class Human implements Comparable<Human> {
        private final String firstName;
        private final String lastName;
        private final int age;

        public Human(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Human otherHuman) {
            int result = this.firstName.compareTo(otherHuman.firstName);
            if (result == 0) {
                result = this.lastName.compareTo(otherHuman.lastName);
            }
            if (result == 0) {
                result = Integer.compare(this.age, otherHuman.age);
            }
            return result;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + ", Age: " + age;
        }
    }

    static class HumanComparatorByLastName implements Comparator<Human> {
        @Override
        public int compare(Human firstHuman, Human secondHuman) {
            return firstHuman.getLastName().compareTo(secondHuman.getLastName());
        }
    }

    public static void thirdTask() {

        // a)
        List<Human> humans = new ArrayList<>();
        humans.add(new Human("Ivan", "Ivanov", 25));
        humans.add(new Human("Alexey", "Kolosov", 19));
        humans.add(new Human("Dmitri", "Medvedev", 47));
        humans.add(new Human("Anna", "Petrova", 36));

        Set<Human> hashSet = new HashSet<>(humans);
        System.out.println("Hashset: ");
        for (Human human : hashSet) {
            System.out.println(human);
        }
        // HashSet не гарантирует порядок элементов и
        // вывод данных происходит в случайном порядке.
        // Использует хеширование для определения уникальности.

        // b)
        Set<Human> linkedHashSet = new LinkedHashSet<>(humans);
        System.out.println("\nLinkedHashSet:");
        for (Human human : linkedHashSet) {
            System.out.println(human);
        }
        // LinkedHashSet сохраняет порядок элементов.
        // Использует связанный список.

        // c)
        Set<Human> treeSet = new TreeSet<Human>(humans);
        System.out.println("\nTreeSet:");
        for (Human human : treeSet) {
            System.out.println(human);
        }
        // Автоматически сортирует элементы на основе реализации
        // интерфейса Comparable

        // d)
        Set<Human> treeSetByLastName = new TreeSet<>(new HumanComparatorByLastName());
        treeSetByLastName.addAll(humans);
        System.out.println("\nTreeSet (Comparator by last Name)");
        for (Human human : treeSetByLastName) {
            System.out.println(human);
        }
        // Сортирует элементы по фамилии,
        // игнорируя другие элементы

        // e)
        Set<Human> treeSetByAge = new TreeSet<>((firstHuman, secondHuman) ->
                Integer.compare(firstHuman.getAge(), secondHuman.getAge()));
        treeSetByAge.addAll(humans);
        System.out.println("\nTreeSet (Comparator by age)");
        for (Human human : treeSetByAge) {
            System.out.println(human);
        }
        // Сортирует по возрасту
    }

    // Задание 4
    public static void fourthTask() {
        String checkString = "word anotherWord anotherWord word megaWord wOrd superWord sUperwoRd";

        String[] words = checkString.toLowerCase().split("\\W+");

        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println("Frequency of words:");
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Задание 5
    public static <K, V> Map<V, List<K>> fifthTask(Map<K, V> inputMap) {
        Map<V, List<K>> reversedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();

            // Если значение уже есть в reversedMap, добавляем ключ в список
            if (!reversedMap.containsKey(value)) {
                reversedMap.put(value, new ArrayList<>());
            }
            reversedMap.get(value).add(key);
        }

        return reversedMap;
    }

}