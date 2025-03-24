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

  public tasks: Task[] = [];

  async get() {
    const fileContent = await fs.readFile(this.filePath, 'utf-8');
    const tasks: Task[] = JSON.parse(fileContent);

    return tasks;
  }

  async add({ title, description }: TaskInput) {
    const tasks = await this.get();

    const task: Task = {
      id: randomUUID(),
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
