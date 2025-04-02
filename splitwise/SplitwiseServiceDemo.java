import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class User {
    private final String userId;
    private final String name;
    private final Map<String, Double> balance;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.balance = new ConcurrentHashMap<>();
    }

    public String getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getBalance() {
        return balance;
    }
}

class Split {
    protected User user;
    protected double amount;

    public Split(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double updatedAmount) {
        this.amount = updatedAmount;
    }
}

class EqualSplit extends Split {
    public EqualSplit(User user) {
        super(user);
    }
}

class ExactSplit extends Split {
    public ExactSplit(User user, Double amount) {
        super(user);
        this.amount = amount;
    }
}

class PercentSplit extends Split {
    private final Double percent;

    public PercentSplit(User user, Double percent) {
        super(user);
        this.percent = percent;
    }

    public Double getPercent() {
        return percent;
    }
}

class Expense {
    private final String expenseId;
    private final String description;
    private final Double amount;
    private final User owner;
    private final List<Split> splits;

    public Expense(String expenseId, String description, Double amount, User owner) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.owner = owner;
        this.splits = new ArrayList<>();
    }

    public String getId() {
        return expenseId;
    }

    public Double getAmount() {
        return amount;
    }

    public User getOwner() {
        return owner;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void addSplit(Split split) {
        splits.add(split);
    }
}

class Group {
    private final String groupId;
    private final String name;
    private final List<User> members;
    private final List<Expense> expenses;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        this.members = new CopyOnWriteArrayList<>();
        this.expenses = new CopyOnWriteArrayList<>();
    }

    public String getId() {
        return groupId;
    }

    public String getName() {
        return name;
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}

class Transaction {
    private final String transactionId;
    private final User sender;
    private final User receiver;
    private final Double amount;

    public Transaction(String transactionId, User sender, User receiver, Double amount) {
        this.transactionId = transactionId;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
}

class SplitwiseService {
    private static SplitwiseService instance;
    private final Map<String, User> usersMap;
    private final Map<String, Group> groupsMap;
    private final Map<String, Transaction> transactionsMap;
    private final AtomicInteger transactionIdCounter = new AtomicInteger(0);

    private SplitwiseService() {
        usersMap = new ConcurrentHashMap<>();
        groupsMap = new ConcurrentHashMap<>();
        transactionsMap = new ConcurrentHashMap<>();
    }

    public static synchronized SplitwiseService getInstance() {
        if (instance == null) {
            instance = new SplitwiseService();
        }
        return instance;
    }

    public void addUser(User user) {
        usersMap.put(user.getId(), user);
    }

    public void addGroup(Group group) {
        groupsMap.put(group.getId(), group);
    }

    public void addExpense(String groupId, Expense expense) {
        Group group = groupsMap.get(groupId);
        if (group != null) {
            group.addExpense(expense);
            System.out.println("");
            System.out.println("=======================");
            System.out.println("New expense with amount " + expense.getAmount() + " is added in group " + group.getName());
            splitExpense(expense);
            updateBalancesAllMembers(expense);
        }
    }

    private void splitExpense(Expense expense) {
        Double totalAmount = expense.getAmount();
        List<Split> splits = expense.getSplits();

        for (Split split : splits) {
            if (split instanceof EqualSplit) {
                split.setAmount(totalAmount / splits.size());
            } else if (split instanceof PercentSplit percentSplit) {
                split.setAmount(percentSplit.getPercent() * totalAmount / 100.0);
            }
        }
    }

    private String getBalanceKey(User user1, User user2) {
        return user1.getId() + ":" + user2.getId();
    }

    private void updateBalance(User user1, User user2, Double amount) {
        String key = getBalanceKey(user1, user2);
        Map<String, Double> user1_balance_map = user1.getBalance();
        user1_balance_map.put(key, user1_balance_map.getOrDefault(key, 0.0) + amount);
        createTransaction(user1, user2, amount);
    }

    private void updateBalancesAllMembers(Expense expense) {
        User owner = expense.getOwner();

        for (Split split : expense.getSplits()) {
            User sender = split.getUser();
            Double amount = split.getAmount();

            if (!owner.equals(sender)) {
                updateBalance(sender, owner, amount);
                updateBalance(owner, sender, -amount);
            }
        }
    }

    private void createTransaction(User user1, User user2, Double amount) {
        String transactionId = generateTransactionId();
        Transaction transaction = new Transaction(transactionId, user1, user2, amount);
        transactionsMap.put(transactionId, transaction);

        if (amount > 0) {
            System.out.println(user1.getName() + " transferred " + amount + " to " + user2.getName() + " successfully.");
        } else {
            System.out.println(user1.getName() + " received " + Math.abs(amount) + " from " + user2.getName() + ".");
        }
    }

    private String generateTransactionId() {
        int transactionId = transactionIdCounter.incrementAndGet();
        return "T" + String.valueOf(transactionId);
    }
}

public class SplitwiseServiceDemo {
    public static void main(String[] args) {
        SplitwiseService splitwiseService = SplitwiseService.getInstance();

        User user1 = new User("U1", "User 1");
        User user2 = new User("U2", "User 2");
        User user3 = new User("U3", "User 3");
        User user4 = new User("U4", "User 4");
        User user5 = new User("U5", "User 5");
        splitwiseService.addUser(user1);
        splitwiseService.addUser(user2);
        splitwiseService.addUser(user3);
        splitwiseService.addUser(user4);
        splitwiseService.addUser(user5);

        Group group1 = new Group("G1", "House mates");
        group1.addMember(user1);
        group1.addMember(user2);
        splitwiseService.addGroup(group1);

        Group group2 = new Group("G2", "Climbing group");
        group2.addMember(user1);
        group2.addMember(user4);
        group2.addMember(user5);
        splitwiseService.addGroup(group2);

        // Create expense
        Expense expense1 = new Expense("E1", "Jan rent", 1000.0, user1);
        EqualSplit equalSplit1 = new EqualSplit(user1);
        EqualSplit equalSplit2 = new EqualSplit(user2);
        expense1.addSplit(equalSplit1);
        expense1.addSplit(equalSplit2);

        Expense expense2 = new Expense("E2", "Saturday lunch", 50.0, user5);
        EqualSplit equalSplit3 = new EqualSplit(user1);
        PercentSplit percentSplit1 = new PercentSplit(user4, 10.0);

        expense2.addSplit(equalSplit3);
        expense2.addSplit(percentSplit1);

        splitwiseService.addExpense(group1.getId(), expense1);
        splitwiseService.addExpense(group2.getId(), expense2);
    }
}