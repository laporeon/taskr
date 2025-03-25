import fs from 'node:fs/promises';
import path from 'node:path';

import { TaskStatus, Colors } from '@enums/index';
import { Task, TaskInput } from '@interfaces/Task';

export class TaskRepository {
  private readonly filePath = path.resolve(
    __dirname,
    '../../output/tasks.json',
  );

  async generateId() {
    const tasks = await this.get('');

    return tasks.length + 1;
  }

  async render(tasks: Task[]) {
    console.log(`\n  ${Colors.underline}Tasks${Colors.reset}`);
    return tasks.map(({ id, title }: Task) => {
      console.log(`   ${Colors.gray}${id}. ${Colors.white}${title}`);
    });
  }

  async get(status: string) {
    const fileContent = await fs.readFile(this.filePath, 'utf-8');
    const tasks: Task[] = JSON.parse(fileContent);

    if (!status) {
      return tasks;
    }

    return tasks.filter(task => task.status === status);
  }

  async add({ title, priority }: TaskInput) {
    const tasks = await this.get('');

    const id = await this.generateId();

    const task: Task = {
      id,
      title,
      priority,
      status: TaskStatus.TODO,
      createdAt: new Date().toISOString(),
    };

    tasks.push(task);

    await fs.writeFile(this.filePath, JSON.stringify(tasks, null, 2));
    console.log(
      `\n${Colors.green}✔ ${Colors.gray}Task successfully created!${Colors.reset}`,
    );
  }
}
