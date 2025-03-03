import { Component } from '@angular/core';
import { MonsterService } from '../services/monster.service';
import { MonsterBaseDTO } from '../models/monster/MonsterBaseDTO.model';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: false,
})
export class HomePage {

  monsters: MonsterBaseDTO[] = [];

  constructor(private monsterService: MonsterService) {

    this.monsterService.getMonstersWithPaginacion().subscribe(res => {
      console.log(res);
      this.monsters = res.content;
      
    });

  }

}
