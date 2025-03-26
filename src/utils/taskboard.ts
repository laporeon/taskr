import { Colors, TaskPrioritySymbols, TaskStatusSymbols } from '@enums/index';
import { Task } from '@interfaces/Task';

const renderTaskboard = (tasks: Task[]) => {
  const toDoTasksCount = tasks.filter(task => task.status === 'todo').length;
  const inProgressTasksCount = tasks.filter(
    task => task.status === 'in-progress',
  ).length;
  const finishedTasksCount = tasks.filter(
    task => task.status === 'done',
  ).length;

  console.log(`\n  ${Colors.bold_underline}Tasks${Colors.reset}`);
  tasks.map(({ id, title, status, priority }: Task) => {
    const statusSymbol = TaskStatusSymbols[status];
    const prioritySymbol = TaskPrioritySymbols[priority];

    console.log(
      `   ${Colors.gray}${id}. ${statusSymbol}${title} ${prioritySymbol}`,
    );
  });

  // TODO: refactor this log
  console.log(
    [
      `\n${Colors.orange}${toDoTasksCount} ${Colors.reset}todo`,
      `${Colors.blue}${inProgressTasksCount} ${Colors.reset}in-progress`,
      `${Colors.green}${finishedTasksCount} ${Colors.reset}done\n`,
    ].join(' · '),
  );
};

export { renderTaskboard };
