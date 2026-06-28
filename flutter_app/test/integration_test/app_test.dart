import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:integration_test/integration_test.dart';
import 'package:flutter_app/main.dart';

void main() {
  IntegrationTestWidgetsFlutterBinding.ensureInitialized();

  group('QA Portfolio Demo App', () {
    testWidgets('app renders with correct title', (tester) async {
      await tester.pumpWidget(const MyApp());
      expect(find.text('QA Portfolio Demo'), findsOneWidget);
      expect(find.text('Counter'), findsOneWidget);
      expect(find.text('Todo List'), findsOneWidget);
    });

    testWidgets('counter starts at zero', (tester) async {
      await tester.pumpWidget(const MyApp());
      expect(find.text('0'), findsOneWidget);
    });

    testWidgets('increment button increments counter', (tester) async {
      await tester.pumpWidget(const MyApp());
      await tester.tap(find.text('Increment'));
      await tester.pump();
      expect(find.text('1'), findsOneWidget);
    });

    testWidgets('multiple increments show correct count', (tester) async {
      await tester.pumpWidget(const MyApp());
      for (var i = 0; i < 3; i++) {
        await tester.tap(find.text('Increment'));
        await tester.pump();
      }
      expect(find.text('3'), findsOneWidget);
    });

    testWidgets('add a todo item', (tester) async {
      await tester.pumpWidget(const MyApp());
      await tester.enterText(find.byType(TextField), 'Write tests');
      await tester.tap(find.text('Add'));
      await tester.pump();
      expect(find.text('Write tests'), findsOneWidget);
    });

    testWidgets('toggle todo completion', (tester) async {
      await tester.pumpWidget(const MyApp());
      await tester.enterText(find.byType(TextField), 'Toggle me');
      await tester.tap(find.text('Add'));
      await tester.pump();
      await tester.tap(find.byType(Checkbox));
      await tester.pump();
      final checkbox = tester.widget<Checkbox>(find.byType(Checkbox));
      expect(checkbox.value, true);
    });

    testWidgets('delete a todo item', (tester) async {
      await tester.pumpWidget(const MyApp());
      await tester.enterText(find.byType(TextField), 'Delete me');
      await tester.tap(find.text('Add'));
      await tester.pump();
      expect(find.text('Delete me'), findsOneWidget);
      await tester.tap(find.byIcon(Icons.delete));
      await tester.pump();
      expect(find.text('Delete me'), findsNothing);
    });
  });
}
