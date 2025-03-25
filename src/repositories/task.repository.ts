import { randomUUID } from 'node:crypto';
import fs from 'node:fs/promises';
import path from 'node:path';

import { Task, TaskInput } from '@interfaces/Task';
import { TaskStatus } from '@utils/taskStatus';

export class TaskRepository {
  private readonly filePath = path.resolve(
    __dirname,
    '../../output/tasks.json',
  );

  async generateId() {
    const tasks = await this.get('');

    return tasks.length + 1;
  }

  async get(status: string) {
    const fileContent = await fs.readFile(this.filePath, 'utf-8');
    const tasks: Task[] = JSON.parse(fileContent);

    if (!status) {
      return tasks;
    }

    return tasks.filter(task => task.status === status);
  }

  async add({ title, description }: TaskInput) {
    const tasks = await this.get('');

    const id = await this.generateId();

    const task: Task = {
      id,
      title,
      description,
      status: TaskStatus.TODO,
      createdAt: new Date().toISOString(),
    };

    tasks.push(task);

    await fs.writeFile(this.filePath, JSON.stringify(tasks, null, 2));
    console.log('Task successfully added!');
  }
}
