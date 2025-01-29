// Задача 1
class Database {
  static Database? _instance;

  factory Database() {
    if (_instance == null) {
      _instance = Database._();
      print('Создано подключение к базе данных');
    }
    return _instance!;
  }

  Database._();

  void test() {
    print('Этот метод вызван из Database');
  }
}
// Задача 2
class Logger {
  static Logger? _instance;
  static List<String> _logs = [];

  factory Logger() {
    if (_instance == null) {
      _instance = Logger._();
    }
    return _instance!;
  }

  Logger._();

  void addLog(String message) {
    _logs.add(message);
  }

  void printLogs() {
    for (var log in _logs) {
      print(log);
    }
  }
}
// Задача 3
enum OrderStatus { NEW, IN_PROGRESS, DELIVERED, CANCELLED }

class Order {
  String _id;
  OrderStatus _status;

  Order(this._id) : _status = OrderStatus.NEW;

  void changeStatus(OrderStatus newStatus) {
    if (_status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
      print('Нельзя отменить доставленный заказ');
      return;
    }

    _status = newStatus;
    print('Статус заказа изменен на $_status');
  }

  void printStatus() {
    print('Текущий статус заказа: $_status');
  }
}
// Задача 4
enum Season { WINTER, SPRING, SUMMER, AUTUMN }

String getSeasonName(Season season) {
  switch (season) {
    case Season.WINTER:
      return 'Зима';
    case Season.SPRING:
      return 'Весна';
    case Season.SUMMER:
      return 'Лето';
    case Season.AUTUMN:
      return 'Осень';
  }
}

void main() {
  Database db1 = Database();
  Database db2 = Database();

  print(db1 == db2); // true

  db1.test();

  Logger logger = Logger();
  logger.addLog('Лог 1');
  logger.addLog('Лог 2');
  logger.printLogs();

  Order order = Order('123');
  order.printStatus();
  order.changeStatus(OrderStatus.IN_PROGRESS);
  order.changeStatus(OrderStatus.DELIVERED);
  order.changeStatus(OrderStatus.CANCELLED);

  print(getSeasonName(Season.SPRING));
}
