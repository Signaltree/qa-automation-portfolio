import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'QA Portfolio Demo',
      theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  final List<TodoItem> _todos = [];
  final _todoController = TextEditingController();

  void _increment() => setState(() => _counter++);

  void _addTodo() {
    if (_todoController.text.trim().isEmpty) return;
    setState(() => _todos.add(TodoItem(text: _todoController.text.trim())));
    _todoController.clear();
  }

  void _toggleTodo(int i) => setState(() => _todos[i].done = !_todos[i].done);

  void _deleteTodo(int i) => setState(() => _todos.removeAt(i));

  @override
  void dispose() {
    _todoController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: const Text('QA Portfolio Demo'),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          const Text('Counter',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600)),
          const SizedBox(height: 8),
          Text('$_counter',
              style:
                  const TextStyle(fontSize: 48, fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          ElevatedButton(onPressed: _increment, child: const Text('Increment')),
          const Divider(height: 32),
          const Text('Todo List',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w600)),
          const SizedBox(height: 8),
          Row(
            children: [
              Expanded(
                  child: TextField(
                      controller: _todoController,
                      decoration:
                          const InputDecoration(hintText: 'Add todo...'))),
              const SizedBox(width: 8),
              ElevatedButton(onPressed: _addTodo, child: const Text('Add')),
            ],
          ),
          const SizedBox(height: 8),
          ..._todos.asMap().entries.map((e) => ListTile(
                leading: Checkbox(
                    value: e.value.done, onChanged: (_) => _toggleTodo(e.key)),
                title: Text(e.value.text,
                    style: TextStyle(
                        decoration:
                            e.value.done ? TextDecoration.lineThrough : null)),
                trailing: IconButton(
                    icon: const Icon(Icons.delete),
                    onPressed: () => _deleteTodo(e.key)),
              )),
        ],
      ),
    );
  }
}

class TodoItem {
  final String text;
  bool done;
  TodoItem({required this.text, this.done = false});
}
