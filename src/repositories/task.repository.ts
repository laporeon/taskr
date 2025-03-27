import fs from 'node:fs/promises';
import path from 'node:path';

import { TaskStatus, Colors } from '@enums/index';
import { CreateTaskInput, Task } from '@interfaces/Task';

export class TaskRepository {
  private readonly filePath = path.resolve(
    __dirname,
    '../../output/tasks.json',
  );

  async generateId() {
    const tasks = await this.get();

    return tasks.length + 1;
  }

  async add({ title, priority }: CreateTaskInput) {
    const tasks = await this.get();

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
    console.log(`\n${Colors.green}✔ ${Colors.gray}Task successfully created!`);
  }

  async get(status?: TaskStatus | undefined) {
    const fileContent = await fs.readFile(this.filePath, 'utf-8');
    const tasks: Task[] = JSON.parse(fileContent);

    if (!status) {
      return tasks;
    }

    return tasks.filter(task => task.status === status);
  }

  async update(id: number, updatedFields: Partial<Task>) {
    let tasks = await this.get();

    const task = await this.checkIfTaskExists(id, tasks);

    if (!task) return console.error(`${Colors.red}Error: Task not found.`);

    const updatedTask = {
      ...task,
      ...updatedFields,
      updatedAt: new Date().toISOString(),
    };

    tasks.splice(id - 1, 1, updatedTask);
    await fs.writeFile(this.filePath, JSON.stringify(tasks, null, 2));
    console.log(`\n${Colors.green}✔ ${Colors.gray}Task successfully updated!`);
  }

  async delete(id: number) {
    let tasks = await this.get();

    const task = await this.checkIfTaskExists(id, tasks);

    if (!task) return console.error(`${Colors.red}Error: Task not found.`);

    tasks = tasks.filter(task => task.id != id);
    await fs.writeFile(this.filePath, JSON.stringify(tasks, null, 2));
  }

  async checkIfTaskExists(id: number, tasks: Task[]) {
    const task = tasks.find(task => task.id === id);

    return task;
  }
}
